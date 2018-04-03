import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

/* Components */
import {NavigationComponent} from './business/components/navigation/navigation.component';
import {SlidePanelContentComponent} from './business/components/slide-panel/slidePanelContent/slide-panel-content.component';
import {InputComponent} from './business/components/input/input.component';
import {SlidePanelComponent} from './business/components/slide-panel/slide-panel.component';
import {ButtonBackComponent} from './business/components/button-back/button-back.component';

/* Services */
import {DataService} from './business/services/data.service';

/* i18n */
import {registerLocaleData} from '@angular/common';
import localeRu from '@angular/common/locales/ru';
import localeEn from '@angular/common/locales/en';

export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, 'http://localhost:80/frontend/shared/assets/i18n/', '.json');
}

registerLocaleData(localeRu, 'ru');
registerLocaleData(localeEn, 'en');

@NgModule({
    declarations: [
        NavigationComponent,
        SlidePanelComponent,
        SlidePanelContentComponent,
        InputComponent,
        ButtonBackComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule,
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
    exports: [
        NavigationComponent,
        SlidePanelComponent,
        SlidePanelContentComponent,
        InputComponent,
        ButtonBackComponent,
        TranslateModule,
        FormsModule
    ]
})
export class SharedModule {
}
