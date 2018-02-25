import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-success',
    templateUrl: './success.component.html',
    styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {
    public contact = {
        name: 'Чайка Александра',
        email: 'grani.theatre.tickets@gmail.com',
        phone: '+420-608-841-487',
        vk: 'vk.com/die136148122',
        whatsApp: '773-689-106',
        viber: '773-689-106',
        telegram: '773-689-106'
    };

    constructor() {
    }

    ngOnInit() {
    }

    onGoHome() {
        // go Rome
    }
}
