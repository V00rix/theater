import {AfterViewInit, Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {AppState, DataManagementService, DataStatus} from '../services/data-management.service';
import {DatePipe} from '@angular/common';
import {forkJoin} from 'rxjs/observable/forkJoin';
import {Observable} from 'rxjs/Observable';
import {Subject} from 'rxjs/Subject';

@Component({
    selector: 'app-landing',
    templateUrl: './landing.component.html',
    styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit, AfterViewInit, OnDestroy {
    @Output() dataLoaded = new EventEmitter<number>();
    public imagesToLoad: number;
    public imagesUrls: string[];
    private loadingStartedTime: number;
    private datePipe = new DatePipe('En');
    private imagesCached = new Subject<void>();

    constructor(private dms: DataManagementService) {
        this.imagesToLoad = -1;
    }

    ngOnInit() {
    }

    /* After view init we want to load basic performance data */
    ngAfterViewInit() {
        console.log('Basic layout has loaded at ' + this.datePipe.transform(Date.now(), 'HH:mm:ss:SSS'));
        this.loadingStartedTime = Date.now();
        console.log('Loading has started at ' + this.datePipe.transform(this.loadingStartedTime, 'HH:mm:ss:ss:SSS'));

        /* Load images' urls to cache */
        const imagesRequest = this.dms.getImagesUrls();
        /* Load app state */
        const appStateRequest = this.dms.getAppState();

        imagesRequest.subscribe((imagesUrls: string[]) => {
            // console.log(imagesUrls);
            this.imagesUrls = imagesUrls;
            this.imagesToLoad = imagesUrls.length;
            console.log('Image has loaded in ' + this.datePipe.transform(Date.now() - this.loadingStartedTime, 'ss:SSS'));
        });

        appStateRequest.subscribe((appState: AppState) => {
            // console.log(appState);
        });

        this.imagesCached.subscribe(() => {
            const loadingFinishedTime = Date.now();
            console.log('Loading finished at: ' + this.datePipe.transform(loadingFinishedTime, 'HH:mm:ss:ss:SSS'));
            console.log('Loading took: ' + this.datePipe.transform(loadingFinishedTime - this.loadingStartedTime, 'ss:SSS'));
        });

        forkJoin([this.imagesCached, appStateRequest]).subscribe(results => {
            // After both images have been cached and application state has been fetched
            // redirect to page set by application state
            this.dataLoaded.emit(results[1]);
        });
    }

    /* After some image has loaded (has gotten cached) we update load progress */
    imageLoaded() {
        this.imagesCached.next();
        this.imagesToLoad--;
        console.log(this.imagesToLoad + ' images left to load');
        if (this.imagesToLoad === 0) {
            // After the loading has finished we emit an event
            this.imagesCached.complete();
        }
    }

    ngOnDestroy() {
        this.imagesCached.unsubscribe();
    }
}
