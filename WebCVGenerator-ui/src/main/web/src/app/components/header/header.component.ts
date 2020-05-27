import { Component, OnInit } from '@angular/core';
import { LanguageType } from './LanguageType';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  public LanguageType: any = LanguageType;
  private defaultLanguage = LanguageType.EN;
  public selectedLanguage: LanguageType;

  constructor(private translateService: TranslateService) {
    this.selectedLanguage = this.selectedLanguage ? this.selectedLanguage : this.defaultLanguage;
    translateService.setDefaultLang('en');
  }

  ngOnInit() {
  }


  public changeLanguage(selectedLanguage: LanguageType) {
    this.selectedLanguage = selectedLanguage;
    this.translateService.use(selectedLanguage.toString());
  }
}
