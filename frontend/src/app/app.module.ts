import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NotFoundComponent} from './pages/not-found/not-found.component';
import {ErrorComponent} from './pages/error/error.component';
import {PerformancesComponent} from './pages/performances/performances.component';
import {PerformanceDetailComponent} from './pages/performance-detail/performance-detail.component';
import {SceneComponent} from './pages/scene/scene.component';
import {ConfirmationComponent} from './pages/confirmation/confirmation.component';
import {SuccessComponent} from './pages/success/success.component';
import {LandingComponent} from './landing/landing.component';
import {AppRoutingModule} from './app-routing.module';
import {DataManagementService} from './services/data-management.service';
import {BgTestComponent} from './dev/bg-test/bg-test.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {DialogPanelComponent} from './dialog-panel/dialog-panel.component';
import {PersonalDataComponent} from './pages/personal-data/personal-data.component';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {registerLocaleData} from '@angular/common';
import localeRu from '@angular/common/locales/ru';
import localeEn from '@angular/common/locales/en';
import { DateTransformPipe } from './pipes/date-transform.pipe';

// AoT requires an exported function for factories
export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

registerLocaleData(localeRu, 'ru');
registerLocaleData(localeEn, 'en');

@NgModule({
    declarations: [
        AppComponent,
        NotFoundComponent,
        ErrorComponent,
        PerformancesComponent,
        PerformanceDetailComponent,
        SceneComponent,
        PersonalDataComponent,
        ConfirmationComponent,
        SuccessComponent,
        LandingComponent,
        BgTestComponent,
        DialogPanelComponent,
        DateTransformPipe,
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
    providers: [DataManagementService,
        { provide: LOCALE_ID, useValue: 'en' }],
    bootstrap: [AppComponent]
})
export class AppModule {
}
