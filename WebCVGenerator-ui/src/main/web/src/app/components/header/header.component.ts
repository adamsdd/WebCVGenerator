import {Component, OnInit} from '@angular/core';
import {LanguageType, LanguageTypeUtils} from './LanguageType';
import {TranslateService} from '@ngx-translate/core';
import {DateAdapter} from '@angular/material/core';
import {TranslateCacheService} from 'ngx-translate-cache';
import {UserService} from "../../services/user/user.service";
import {Route, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  public LanguageType: any = LanguageType;
  private defaultLanguage = LanguageType.EN;
  public selectedLanguage: LanguageType;

  constructor(private translateService: TranslateService, private dateAdapter: DateAdapter<Date>,
              private translateCacheService: TranslateCacheService, private userService: UserService,
              private router: Router) {
    const cachedLanguage = translateCacheService.getCachedLanguage();
    if (cachedLanguage) {
      translateService.setDefaultLang(cachedLanguage);
      this.selectedLanguage = LanguageTypeUtils.parseLanguageType(cachedLanguage);
    } else {
      this.selectedLanguage = this.selectedLanguage ? this.selectedLanguage : this.defaultLanguage;
      translateService.setDefaultLang('en');
      this.translateCacheService.init();
    }
  }

  ngOnInit() {
  }

  public changeLanguage(selectedLanguage: LanguageType) {
    this.selectedLanguage = selectedLanguage;
    this.translateService.use(selectedLanguage.toString());
    this.dateAdapter.setLocale(selectedLanguage.toString());
    this.translateCacheService.init();
  }

  public logout() {
    this.userService.logout();
    this.router.navigate(['/login']);
  }
}
