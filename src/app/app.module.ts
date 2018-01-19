import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NotFoundComponent} from './pages/not-found/not-found.component';
import {ErrorComponent} from './pages/error/error.component';
import {PerformancesComponent} from './pages/performances/performances.component';
import {PerformanceDetailComponent} from './pages/performance-detail/performance-detail.component';
import {SceneComponent} from './pages/scene/scene.component';
import {PersonalInfoComponent} from './pages/personal-info/personal-info.component';
import {ConfirmationComponent} from './pages/confirmation/confirmation.component';
import {SuccessComponent} from './pages/success/success.component';
import {LandingComponent} from './landing/landing.component';
import {BackgroundTestComponent} from './dev/background-test/background-test.component';
import {DataServiceComponent} from './services/data-service/data-service.component';
import {AppRoutingModule} from './app-routing.module';


@NgModule({
    declarations: [
        AppComponent,
        NotFoundComponent,
        ErrorComponent,
        PerformancesComponent,
        PerformanceDetailComponent,
        SceneComponent,
        PersonalInfoComponent,
        ConfirmationComponent,
        SuccessComponent,
        LandingComponent,
        BackgroundTestComponent,
        DataServiceComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
