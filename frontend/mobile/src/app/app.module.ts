import {SharedModule} from '../../../shared/shared.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {DataService} from '../../../shared/business/services/data.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {ConfirmationComponent} from './pages/confirmation/confirmation.component';
import {SuccessComponent} from './pages/success/success.component';
import {HomeComponent} from './pages/home/home.component';
import {PerformanceComponent} from './pages/performance/performance.component';
import {SessionComponent} from './pages/session/session.component';
import {ViewerComponent} from './pages/viewer/viewer.component';
import {NotFoundComponent} from './pages/not-found/not-found.component';
import {CheckoutComponent} from './pages/checkout/checkout.component';
import { ZoomedComponent } from './pages/session/zoomed/zoomed.component';


@NgModule({
  declarations: [
    AppComponent,
    ConfirmationComponent,
    SuccessComponent,
    HomeComponent,
    PerformanceComponent,
    SessionComponent,
    ViewerComponent,
    NotFoundComponent,
    CheckoutComponent,
    ZoomedComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
