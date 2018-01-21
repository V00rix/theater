import {Component, OnInit} from '@angular/core';
import {DataManagementService} from '../../services/data-management.service';

@Component({
    selector: 'app-confirmation',
    templateUrl: './confirmation.component.html',
    styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {
    private confirmationData: {
        seat: { row: number, seat: number },
        userData: {
            name: string,
            phone: string,
            email: string,
            vk: string,
            whatsApp: string,
            viber: string,
            telegram: string
        }
    }[];

    constructor(private dms: DataManagementService) {
    }

    ngOnInit() {
        this.confirmationData = [
            {
                seat: {row: 6, seat: 7},
                userData: {
                    name: 'Name',
                    phone: '+420777666555',
                    email: 'vladogim@gmail.com',
                    vk: 'vk.com/myVk',
                    whatsApp: null,
                    viber: null,
                    telegram: null
                }
            },
            {
                seat: {row: 6, seat: 7},
                userData: {
                    name: 'Name SdssdA DSdsa aD ASDas',
                    phone: '+420777666555',
                    email: 'vladogim312@gmail.com',
                    vk: 'vk.com/dsadasdas_dsdasdsdsaDSAD',
                    whatsApp: 'dasdasdsdadasd',
                    viber: '_SdsadasdasdAS',
                    telegram: 'dsadasdasdasdasdsa'
                }
            }
        ];
    }

    onConfirmed() {

    }
}
