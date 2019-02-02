import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GoldCardRegisterComponent } from './gold-card-register/gold-card-register.component';
import { LoginComponent , ErrorComponent} from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth/auth.guard';
import { AppComponent } from './app.component';
import { UserviewComponent } from './userview/userview.component';
import { EligibleDiseasesComponent } from './eligible-diseases/eligible-diseases.component';
import { TreatmentHistoryComponent } from './treatment-history/treatment-history.component';
import { ReloadpageComponent } from './reloadpage/reloadpage.component';
import { HospitalRegisterComponent } from './hospital-register/hospital-register.component';
import { AddMidicineComponent } from './add-midicine/add-midicine.component';
import { GDCardComponent } from './gd-card/gd-card.component';


const routes: Routes = [
    { path: '', redirectTo:'/login' , pathMatch: 'full'},
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'login', component: LoginComponent},
    { path: 'EligibleDiseases', component: EligibleDiseasesComponent, canActivate: [AuthGuard]},
    { path: 'TreatmentHistory', component: TreatmentHistoryComponent, canActivate: [AuthGuard]},
    { path: 'reload/:page', component: ReloadpageComponent, canActivate: [AuthGuard]},
    { path: 'userview', component: UserviewComponent, canActivate: [AuthGuard]},
//    { path: '**', redirectTo:'/Error'},
    { path: 'goldCardRegister', component: GoldCardRegisterComponent, canActivate: [AuthGuard]},
    { path: 'addMidicine', component: AddMidicineComponent, canActivate: [AuthGuard]},
    { path: 'hospitalRegister', component: HospitalRegisterComponent, canActivate: [AuthGuard]},
    { path: 'GDCard', component: GDCardComponent, canActivate: [AuthGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

