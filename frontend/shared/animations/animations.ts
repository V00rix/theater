import {trigger, state, style, animate, transition} from '@angular/animations';

export class Animations {
    public static animations = [
        trigger('fadeIn', [
            transition('void => *', [
                style({opacity: 0}),
                animate('0.3s 0.1s ease-out')
            ]),
        ]),
        trigger('fadeInLong', [
            transition('void => *', [
                style({opacity: 0}),
                animate('1s 0.5s ease-out')
            ]),
        ]),
        trigger('fadeOut', [
            transition('* => void', [
                style({opacity: 0}),
                animate('0.3s 0.1s ease-in-out')
            ]),
        ]),
        trigger('slideTop', [
            transition('void => *', [
                style({transform: 'translateX(100%)'}),
                animate('1.3s 1.1s ease-out')
            ]),
        ]),
        trigger('input', [
            state('selected', style({'font-size': '1em'})),
            state('rest', style({'font-size': '2em', 'color': 'rgb(45, 179, 210)'})),
            transition('rest => selected', animate('0.2s 0.01s ease-in')),
            transition('selected => rest', animate('0.2s 0.01s ease-in'))
        ]),
        trigger('input-field', [
            state('empty', style({'top': '-10px', 'font-size': '0'})),
            state('filled', style({'top': '0', 'font-size': '2em'})),
            transition('rest => selected', animate('0.2s 0.01s ease-in')),
            transition('selected => rest', animate('0.1s 0.02s ease-in')),
        ])
    ];
}
