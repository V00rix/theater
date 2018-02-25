import {Component, OnInit} from '@angular/core';
import {DataManagementService} from '../../services/data-management.service';
import {ActivatedRoute, Router} from '@angular/router';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
    selector: 'app-personal-data',
    templateUrl: './personal-data.component.html',
    styleUrls: ['./personal-data.component.scss']
})
export class PersonalDataComponent implements OnInit {
    public personalData: SeatWithUserData[];
    public maxRows: number;
    public bottomPanelButtons = [
        {
            type: 'submit', text: 'confirm', callback: () => {
            }
        }
    ];
    public formsValid = false;

    constructor(private dms: DataManagementService, private router: Router, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        if (!this.dms.seatsSaved) {
            this.dms.seatsSavedCompleted.subscribe(() => {
                this.getSeats();
            });
        } else {
            this.getSeats();
        }
    }

    public onSubmit(form) {
        if (form.valid) {
            this.dms.savePersonalData(this.personalData, this.maxRows);
            this.router.navigate(['./confirmation'], {relativeTo: this.activatedRoute});
        }
    }

    private getSeats(): void {
        this.dms.getSeats().subscribe(
            (seats: {pData: { row: number, seat: number }[], maxRows: number}) => {
                console.log(seats);
                this.personalData = seats.pData.map(seat => new SeatWithUserData(seat.row, seat.seat)) || null;
                this.maxRows = seats.maxRows || 0;
            },
            error => {
                console.log(error);
            }
        );
    }
}

class SeatWithUserData {
    constructor(public row: number, public seat: number,
                public userData?: {
                    name?: string,
                    phone?: string,
                    email?: string,
                    vk?: string,
                    whatsApp?: string,
                    viber?: string,
                    telegram?: string
                }) {
        this.userData = userData || {
            name: null,
            phone: null,
            email: null,
            vk: null,
            whatsApp: null,
            viber: null,
            telegram: null
        };
    }
}
