import {Component, OnInit} from '@angular/core';
import {DataManagementService} from '../../services/data-management.service';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-confirmation',
    templateUrl: './confirmation.component.html',
    styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {
    public confirmationData: {
        row: number, seat: number,
        userData: {
            name: string,
            phone: string,
            email: string,
            vk?: string,
            whatsApp?: string,
            viber?: string,
            telegram?: string
        }
    }[];

    constructor(private dms: DataManagementService,
                private location: Location, private router: Router) {
    }

    ngOnInit() {
        if (!this.dms.personalDataSaved) {
            this.dms.personalDataSavedCompleted.subscribe(() => {
                this.getConfirmationData();
            });
        } else {
            this.getConfirmationData();
        }
    }

    public onConfirm() {
        this.dms.postReservationRequest();
        this.router.navigate(['/success']);
    }

    private getConfirmationData(): void {
        this.dms.getConfirmationData().subscribe(
            (confirmationData: {
                row: number, seat: number,
                userData?: {
                    name?: string,
                    phone?: string,
                    email?: string,
                    vk?: string,
                    whatsApp?: string,
                    viber?: string,
                    telegram?: string
                }
            }[]) => {
                console.log(confirmationData);
                this.confirmationData = confirmationData.map(cd => {
                    return {
                        row: cd.row,
                        seat: cd.seat,
                        userData: {
                            name: cd.userData.name || 'Name is missing! Something has gone wrong!',
                            phone: cd.userData.phone || 'Phone is missing! Something has gone wrong!',
                            email: cd.userData.email || 'Email is missing! Something has gone wrong!',
                            vk: cd.userData.vk,
                            whatsApp: cd.userData.whatsApp,
                            viber: cd.userData.viber,
                            telegram: cd.userData.telegram
                        }
                    };
                });
            },
            error => {
                console.log(error);
            }
        );
    }

    public onGoBack() {
        this.location.back();
    }
}
