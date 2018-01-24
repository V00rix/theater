import {Component, OnInit} from '@angular/core';
import {AppState, DataManagementService} from './services/data-management.service';
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
                private router: Router,
                private route: ActivatedRoute,
                private location: Location) {
    }

    ngOnInit() {

    }

    onDataLoaded(appState: AppState) {
        console.log(`Ok, basic loading complete, your previous state was '${AppState[appState]}' (${appState})`);

        // switch (appState) {
        //     case AppState.Performances:
        //         // Get performance data. then()
        //         //  -> {
        //         //  redirect to page
        //         //  hide loading overlay
        //         // }
        //         this.router.navigate(['/performances']);
        //         break;
        //     case AppState.PerformanceDetail:
        //         this.router.navigate(['/performances/' + this.dms.performanceId]);
        //         break;
        //     case AppState.Scene:
        //         break;
        //     case AppState.PersonalData:
        //         break;
        //     case AppState.Confirmation:
        //         break;
        //     case AppState.Success:
        //         break;
        //     default:
        //         break;
        // }
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
