import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ContinentsService {
  public continents: Observable<Array<string>>;

  constructor(private httpClient: HttpClient) {
    this.continents = httpClient
      .get('http://localhost:8080/api/countries')
      .pipe(
        map(countries => countries as Array<any>),
        map(countries => this.extractRegion(countries)),
        map(continents => this.unique(continents)),
        map(continents => this.addMissingNames(continents))
      );
  }

  private extractRegion(countries: Array<any>): Array<string> {
    return countries.map(c => c.region);
  }

  private unique(countries: Array<string>): Array<string> {
    return [...new Set(countries)];
  }

  private addMissingNames(continents: Array<string>): Array<string> {
    return continents.map(c => (c ? c : 'Other'));
  }
}
