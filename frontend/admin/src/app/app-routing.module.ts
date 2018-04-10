import {NgModule} from '@angular/core';

import {Routes, RouterModule} from '@angular/router';
import {AuthorizationComponent} from './pages/authorization/authorization.component';
import {HomeComponent} from './pages/home/home.component';
import {SessionsComponent} from './pages/sessions/sessions.component';
import {ViewersComponent} from './pages/viewers/viewers.component';

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'authorization', component: AuthorizationComponent},
  {path: 'sessions', component: SessionsComponent},
  {path: 'sessions/:id/viewers', component: ViewersComponent},
  {path: '**', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(
    appRoutes
  )],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
