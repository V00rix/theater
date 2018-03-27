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

/* Components */
import {NavigationComponent} from './business/components/navigation/navigation.component';
import { SlidePanelContentComponent } from './business/components/slide-panel/slidePanelContent/slide-panel-content.component';
import { InputComponent } from './business/components/input/input.component';
import { SlidePanelComponent } from './business/components/slide-panel/slide-panel.component';

/* Services */
import {DataService} from './business/services/data.service';

/* i18n */
import {registerLocaleData} from '@angular/common';
import localeRu from '@angular/common/locales/ru';
import localeEn from '@angular/common/locales/en';
import { ButtonBackComponent } from './business/components/button-back/button-back.component';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

registerLocaleData(localeRu, 'ru');
registerLocaleData(localeEn, 'en');

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
    SessionsComponent,
    ViewerComponent,
    NavigationComponent,
    NotFoundComponent,
    SlidePanelComponent,
    SlidePanelContentComponent,
    InputComponent,
    CheckoutComponent,
    ButtonBackComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    })
  ],
  providers: [DataService,
    {provide: LOCALE_ID, useValue: 'en'}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
