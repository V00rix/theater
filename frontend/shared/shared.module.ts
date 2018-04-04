import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

/* Components */
import {NavigationComponent} from './business/components/navigation/navigation.component';
import {SlidePanelContentComponent} from './business/components/slide-panel/slidePanelContent/slide-panel-content.component';
import {InputComponent} from './business/components/input/input.component';
import {SlidePanelComponent} from './business/components/slide-panel/slide-panel.component';
import {ButtonBackComponent} from './business/components/button-back/button-back.component';

/* Services */
import {DataService} from './business/services/data.service';

/* i18n */
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {registerLocaleData} from '@angular/common';
import localeRu from '@angular/common/locales/ru';
import localeEn from '@angular/common/locales/en';

export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http);
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
        BrowserAnimationsModule,
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
    providers: [DataService],
    exports: [
        NavigationComponent,
        SlidePanelComponent,
        SlidePanelContentComponent,
        InputComponent,
        ButtonBackComponent,
        FormsModule,
        TranslateModule,
        BrowserAnimationsModule
    ]
})
export class SharedModule {
}
