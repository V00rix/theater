import {NgModule} from '@angular/core';

import {Routes, RouterModule} from '@angular/router';
import {SessionComponent} from './pages/session/session.component';
import {PerformanceComponent} from './pages/performance/performance.component';
import {ConfirmationComponent} from './pages/confirmation/confirmation.component';
import {HomeComponent} from './pages/home/home.component';
import {SuccessComponent} from './pages/success/success.component';
import {CheckoutComponent} from './pages/checkout/checkout.component';
import {ViewerComponent} from './pages/viewer/viewer.component';
import {NotFoundComponent} from './pages/not-found/not-found.component';

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'confirmation', component: ConfirmationComponent},
  {path: 'performance', component: PerformanceComponent},
  {path: 'session', component: SessionComponent},
  {path: 'success', component: SuccessComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: 'viewer', component: ViewerComponent},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(
    appRoutes
  )],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
