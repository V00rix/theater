import {Component, ElementRef, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DataManagementService, DataStatus} from '../../services/data-management.service';
import {Availability, Seat} from '../../models/seat';
import {Viewer} from '../../models/viewer';

@Component({
    selector: 'app-scene',
    templateUrl: './scene.component.html',
    styleUrls: ['./scene.component.scss']
})
export class SceneComponent implements OnInit {
    public sessionData: {
        performanceTitle: String,
        sessionDateTime: number,
        seats: Seat[][]
    } = null;

    public seats: { row: number, seat: number }[] = []; // seats data (row, seat)
    public selectedSeats = []; // as DOM Elements
    public bottomPanelButtons = [
        {
            type: 'button', text: 'Confirm', callback: () => {
                this.dms.saveSeats(this.seats);
                this.router.navigate(['/personal-detail']);
            }
        }
    ];

    private dataStatus = 0; // 0 - nothing has yet been loaded, 1 - only basic or only performance data, 2- - both

    constructor(private dms: DataManagementService, private router: Router, private route: ActivatedRoute) {
    }

    ngOnInit() {
        /* Get session time from router params */
        const dataStatus = this.dms.dataStatus;
        this.route.params.subscribe(params => {
            const sessionTime = +params['sessionTime'];
            const performanceId = +params['performanceId'];
            if (this.dms.dataStatus as DataStatus !== DataStatus.SessionFetchCompleted) {
                this.dms.sessionDataLoaded.subscribe(() => {
                    this.dms.dataStatus = dataStatus;
                    this.sessionData = this.dms.sessionData;
                });
                if (this.dms.dataStatus as DataStatus !== DataStatus.SessionFetchStarted) {
                    this.dms.getSessionData(performanceId, sessionTime);
                }
            } else {
                this.dms.dataStatus = dataStatus;
                this.sessionData = this.dms.sessionData;
            }
        });
    }

    public getClass(row: number, seat: number) {
        switch (this.sessionData.seats[row][seat].availability) {
            case Availability.Hidden:
                return {'inactive': true};
            case Availability.Booked:
                return {'booked': true};
            case Availability.Pending:
                return {'pending': true};
            case Availability.Available:
                return {'available': true};
        }
    }

    public onSeatSelected(event: Event, row: number, seat: number): void {
        if (event.srcElement.classList.contains('booked') ||
            event.srcElement.classList.contains('pending') ||
            event.srcElement.classList.contains('inactive')) {
            return;
        }
        const index: number = this.selectedSeats.indexOf(event.srcElement, 0);
        if (index > -1) {
            event.srcElement.classList.remove('selected');
            this.selectedSeats.splice(index, 1);
            this.seats.splice(index, 1);
        } else {
            event.srcElement.classList.add('selected');
            this.selectedSeats.push(event.srcElement);
            this.seats.push({row: row, seat: seat});
        }
    }

    public getLongestRow(): number {
        let highestCount = 0;
        for (const row of this.sessionData.seats) {
            if (row.length > highestCount) {
                highestCount = row.length;
            }
        }
        return highestCount;
    }
}
