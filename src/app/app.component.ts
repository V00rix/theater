import {Component, OnInit} from '@angular/core';
import {DataManagementService} from './services/data-management.service';
import {Subscription} from 'rxjs/Subscription';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
    public sectionsShown = false;
    public loggedIn = false;

    constructor(private dms: DataManagementService,
                private router: Router, private location: Location) {
    }

    ngOnInit() {

    }

    onDataLoaded() {
        this.sectionsShown = true;
    }

    navigateBack() {
        this.location.back();
    }

    displayReturn() {
        console.log(this.router.url);
        return !(this.router.url === '/' || this.router.url === '/performances');
    }
}
