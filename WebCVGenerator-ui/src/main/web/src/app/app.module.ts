import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';

import {HttpClientModule, HttpClient, HttpInterceptor, HTTP_INTERCEPTORS} from '@angular/common/http';
import {TranslateModule, TranslateLoader, TranslateService} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { BasicInfoComponent } from './components/basic-info/basic-info.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { EducationComponent } from './components/education/education.component';
import { JobExperienceComponent } from './components/job-experience/job-experience.component';
import { SkillsComponent } from './components/skills/skills.component';
import { HobbyComponent } from './components/hobby/hobby.component';
import { GenerateComponent } from './components/generate/generate.component';
import { TranslateCacheModule, TranslateCacheSettings, TranslateCacheService } from 'ngx-translate-cache';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import {RouterModule} from '@angular/router';
import {AuthGuardService as AuthGuard} from './services/auth-guard.service';
import {JWT_OPTIONS, JwtHelperService} from '@auth0/angular-jwt';
import {AuthenticationInterceptor} from './config/AuthenticationInterceptor';
import { EducationModalComponent } from './components/education/education-modal/education-modal.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { JobExperienceModalComponent } from './components/job-experience/job-experience-modal/job-experience-modal.component';
import { SkillsModalComponent } from './components/skills/skills-modal/skills-modal.component';
import { RegistrationModalComponent } from './components/login/registration-modal/registration-modal.component';

export function translateHttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BasicInfoComponent,
    EducationComponent,
    JobExperienceComponent,
    SkillsComponent,
    HobbyComponent,
    GenerateComponent,
    LoginComponent,
    MainComponent,
    EducationModalComponent,
    JobExperienceModalComponent,
    SkillsModalComponent,
    RegistrationModalComponent,
  ],
  imports: [
    MDBBootstrapModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'home', component: MainComponent, canActivate: [AuthGuard]},
      {path: 'login', component: LoginComponent},
      {path: 'logout', component: LoginComponent, runGuardsAndResolvers: 'always'},
      {path: '', redirectTo: 'login', pathMatch: 'full'},
      ], {anchorScrolling: 'enabled'}),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: translateHttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    TranslateCacheModule.forRoot({
      cacheService: {
        provide: TranslateCacheService,
        useFactory: translateCacheFactory,
        deps: [TranslateService, TranslateCacheSettings]
      },
      cacheMechanism: 'Cookie'
    })
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    },
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'}, MatDatepickerModule,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS }, JwtHelperService ],

  bootstrap: [AppComponent]
})
export class AppModule { }

export function translateCacheFactory(
  translateService: TranslateService,
  translateCacheSettings: TranslateCacheSettings
) {
  return new TranslateCacheService(translateService, translateCacheSettings);
}
