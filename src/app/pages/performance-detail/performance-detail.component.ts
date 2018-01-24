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
    public performance: { title: string, imageUrl: string, description: string, sessions: Date[] };
    public groupedSessions: { date: Date, times: Date[] }[] = [];

    /**
     * Groups sessions by day
     * @param {Date[]} sessions
     * @returns {{date: Date; times: Date[]}[]} grouped sessions
     */
    static groupSessions(sessions: Date[]): { date: Date, times: Date[] }[] {

        // Sorting (redundant?)
        sessions.sort((a, b) => a.getTime() < b.getTime() ? -1 : 1);
        /*
            Grouping sessions by day
            ** Nightmarish **
         */
        let nextDayMargin = new Date(sessions[0]).getTime() -
            (new Date(sessions[0]).getTime() % 86400000) + 86400000; // 24 * 60 * 60 * 1000

        const groupedSessions = [];

        groupedSessions.push({
            date: new Date(sessions[0]),
            times: []
        });

        // go through all sessions
        sessions.forEach(session => {
            if (new Date(session).getTime() < nextDayMargin) {
                groupedSessions[groupedSessions.length - 1].times.push(new Date(session));
            } else {
                nextDayMargin += 86400000;
                groupedSessions.push({
                    date: new Date(session),
                    times: [new Date(session)]
                });
            }
        });

        return groupedSessions;
    }

    constructor(private dms: DataManagementService,
                private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
            const performanceId = +params['performanceId'];
            this.dms.getPerformanceDetail(performanceId).subscribe(
                (performance: { title: string, imageUrl: string, description: string, sessions: Date[] }) => {
                    console.log(performance.sessions);
                    this.performance = performance;
                    this.groupedSessions = PerformanceDetailComponent.groupSessions(performance.sessions);
                }
            );
        });
    }


    onSessionSelected(sessionTime: number) {
        // this.dms.getSessionData(this.performanceId, sessionTime);
    }

    ngOnDestroy() {
    }

    test() {
        console.log(this.groupedSessions);
    }
}
