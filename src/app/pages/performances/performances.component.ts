import {Component, OnInit} from '@angular/core';
import {DataManagementService} from '../../services/data-management.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-performances',
    templateUrl: './performances.component.html',
    styleUrls: ['./performances.component.scss']
})
export class PerformancesComponent implements OnInit {
    public performances: {title: string, imageUrl: string}[] = [];

    constructor(public dms: DataManagementService,
                private router: Router) {
    }

    ngOnInit() {
        this.dms.getPerformances().subscribe((performances: {title: string, imageUrl: string}[]) => {
            this.performances = performances;
        });
    }

    displayPerformanceDetail(id: number) {
        this.dms.getPerformanceDetail(id);
        this.router.navigate([`./performances/${id}`]);
    }
}
