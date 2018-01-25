import {NgModule} from '@angular/core';

import {Routes, RouterModule} from '@angular/router';
import {SceneComponent} from './pages/scene/scene.component';
import {NotFoundComponent} from './pages/not-found/not-found.component';
import {PerformancesComponent} from './pages/performances/performances.component';
import {PerformanceDetailComponent} from './pages/performance-detail/performance-detail.component';
import {PersonalDataComponent} from './pages/personal-data/personal-data.component';
import {ConfirmationComponent} from './pages/confirmation/confirmation.component';
import {SuccessComponent} from './pages/success/success.component';
import {ErrorComponent} from './pages/error/error.component';

const appRoutes: Routes = [
    {path: '', component: PerformancesComponent},
    {path: 'performances', component: PerformancesComponent},
    {path: 'home', component: PerformancesComponent},
    {path: 'performances/:performanceId', component: PerformanceDetailComponent},
    {path: 'performances/:performanceId/:sessionTime', component: SceneComponent},
    {path: 'personal-data', component: PersonalDataComponent},
    {path: 'personal-data/confirmation', component: ConfirmationComponent},
    {path: 'success', component: SuccessComponent},
    {path: 'error', component: ErrorComponent},
    {path: 'not-found', component: NotFoundComponent},
    {path: '**', redirectTo: '/not-found'}
];

@NgModule({
    imports: [RouterModule.forRoot(
        appRoutes
    ), ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
