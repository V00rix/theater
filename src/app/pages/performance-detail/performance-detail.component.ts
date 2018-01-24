import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DataManagementService, DataStatus} from '../../services/data-management.service';
import {Performance} from '../../models/performance';
import {HttpClient} from '@angular/common/http';
import {Session} from '../../models/session';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
    selector: 'app-performance-detail',
    templateUrl: './performance-detail.component.html',
    styleUrls: ['./performance-detail.component.scss']
})
export class PerformanceDetailComponent implements OnInit, OnDestroy {
    public performance: { title: string, imageUrl: string, description: string, sessions: Date[] }
    public groupedSessions: { date: Date, times: Date[] }[] = [];

    constructor(private dms: DataManagementService,
                private route: ActivatedRoute) {
    }

    ngOnInit() {
        /* Get performance id */
        this.route.params.subscribe(params => {
            const performanceId = +params['performanceId'];
            this.dms.getPerformanceDetail(performanceId).subscribe(
                (performance: { title: string, imageUrl: string, description: string, sessions: Date[] }) => {
                    this.performance = performance;
                    this.groupedSessions = this.groupSessions(performance.sessions);
                }
            );
            // /* Check if basic data is loaded */
            // if (this.dms.dataStatus < DataStatus.PerformanceFetchCompleted) {
            //     this.dms.performanceDataLoaded.subscribe(() => {
            //         this.onPerformancesLoaded(this.performanceId);
            //     });
            //     if (this.dms.dataStatus as DataStatus !== DataStatus.PerformanceFetchStarted) {
            //
            //     }
            // } else {
            //     this.onPerformancesLoaded(this.performanceId);
            // }
        });
    }

    /**
     * After performances were loaded in DMS
     * @param {number} performanceId
     */
    private groupSessions(sessions: Date[]) {
        // Sorting (redundant?)
        this.performance.sessions.sort((a, b) => a.date < b.date ? -1 : 1);
        /*
            Grouping sessions by day
            ** Nightmarish **
         */
        let nextDayMargin = this.performance.sessions[0].date.getTime() -
            (this.performance.sessions[0].date.getTime() % 86400000) + 86400000; // 24 * 60 * 60 * 1000

        const groupedSessions = [];
        groupedSessions.push({
            date: this.performance.sessions[0].date,
            times: []
        });

        // go through all sessions
        this.performance.sessions.forEach(session => {
            if (session.date.getTime() < nextDayMargin) {
                this.groupedSessions[this.groupedSessions.length - 1].times.push(session.date);
            } else {
                nextDayMargin += 86400000;
                this.groupedSessions.push({
                    date: session.date,
                    times: [session.date]
                });
            }
        });
        this.sessionsSorted = true;
    }

    onSessionSelected(sessionTime: number) {
        this.dms.getSessionData(this.performanceId, sessionTime);
    }

    ngOnDestroy() {
    }
}
