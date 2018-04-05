/* Shared */
import {SharedModule} from '../../../shared/shared.module';

/* Angular */
import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

/* Pages */
import {HomeComponent} from './pages/home/home.component';
import {ContactsComponent} from './pages/contacts/contacts.component';
import {PerformanceComponent} from './pages/performance/performance.component';
import {PerformancesComponent} from './pages/performances/performances.component';
import {SessionComponent} from './pages/session/session.component';
import {SessionsComponent} from './pages/sessions/sessions.component';
import {ViewerComponent} from './pages/viewer/viewer.component';
import {ConfirmationComponent} from './pages/confirmation/confirmation.component';
import {SuccessComponent} from './pages/success/success.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import {ZoomedComponent} from './pages/session/zoomed/zoomed.component';

@NgModule({
  declarations: [
    AppComponent,
    PerformancesComponent,
    ConfirmationComponent,
    SuccessComponent,
    HomeComponent,
    ContactsComponent,
    PerformanceComponent,
    SessionComponent,
    ZoomedComponent,
    SessionsComponent,
    ViewerComponent,
    NotFoundComponent,
    CheckoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
