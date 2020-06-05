export enum LanguageType {
  PL = 'pl',
  EN = 'en'
}


export class LanguageTypeUtils {

  static parseLanguageType(language: string) {
    if (language === 'pl') {
      return LanguageType.PL;
    } else if (language === 'en') {
      return LanguageType.EN;
    }

    throw new TypeError('Cannot parse language for present string = ' + language);
  }
}
