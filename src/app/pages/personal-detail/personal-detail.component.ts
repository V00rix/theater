import {Component, OnInit} from '@angular/core';
import {DataManagementService} from '../../services/data-management.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-personal-detail',
    templateUrl: './personal-detail.component.html',
    styleUrls: ['./personal-detail.component.scss']
})
export class PersonalInfoComponent implements OnInit {
    public seats: {
        row: number; seat: number,
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
    public bottomPanelButtons = [
        {
            type: 'submit', text: 'Confirm', callback: () => {}
        }
    ];
    public testModel = '';
    public displaySome = false;

    constructor(private dms: DataManagementService, private router: Router, private activatedRoute: ActivatedRoute) {
    }


    ngOnInit() {
        // this.dms.getSeats().then(
        //     success => {
        //         this.seats = success;
        //         console.log(this.seats);
        //     },
        //     error => {
        //         console.log(error);
        //     }
        // );
        this.seats = [
            {row: 3, seat: 5, userData: {
                    name: null,
                    phone: null,
                    email: null,
                    vk: null,
                    whatsApp: null,
                    viber: null,
                    telegram: null
                }},
            {row: 3, seat: 6, userData: {
                    name: null,
                    phone: null,
                    email: null,
                    vk: null,
                    whatsApp: null,
                    viber: null,
                    telegram: null
                }},
            {row: 2, seat: 10, userData: {
                    name: null,
                    phone: null,
                    email: null,
                    vk: null,
                    whatsApp: null,
                    viber: null,
                    telegram: null
                }}
        ];
    }

    onSubmit() {
        if (true) {
            this.router.navigate(['./confirmation'], {relativeTo: this.activatedRoute});
        }
    }

    onInput(data) {
        console.log(data);
        this.displaySome = true;
    }
}
