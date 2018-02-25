import {Component, OnInit} from '@angular/core';
import {AppState, DataManagementService} from './services/data-management.service';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';

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
                private location: Location, translate: TranslateService) {
        // this language will be used as a fallback when a translation isn't found in the current language
        translate.setDefaultLang('en');

        // the lang to use, if the lang isn't available, it will use the current loader to get them
        translate.use('ru');
    }

    ngOnInit() {

    }

    onDataLoaded(appState: AppState) {
        console.log(`Ok, basic loading complete, your previous state was '${AppState[appState]}' (${appState})`);
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
