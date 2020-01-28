import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, filter } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Country } from './country';
import { PartiallyFilledCountry } from './partially-filled-country';

@Injectable({
  providedIn: 'root'
})
export class CountriesService {
  constructor(private http: HttpClient) {}

  /** if the REST service is not available, this method can serve the data from a local file */
  public countriesByContinentLocal(
    continent: string
  ): Observable<Array<Country>> {
    return this.http.get('/assets/countries.json').pipe(
      map((list: Array<any>) =>
        list.map(country => this.relevantData(country))
      ),
      map(list => list.filter(country => country.continent === continent))
    );
  }

  public countriesByContinent(continent: string): Observable<Array<Country>> {
    return this.http.get('http://localhost:8080/api/countries').pipe(
      map((list: Array<any>) =>
        list.map(country => this.relevantData(country))
      ),
      map(list => list.filter(country => country.continent === continent))
    );
  }

  private relevantData(country: any): Country {
    return {
      id: country.id,
      name: country.name,
      flag: country.flag,
      continent: country.region,
      population: country.population,
      capital: country.capital
    } as Country;
  }

  /** if the REST service is not available, this method can serve the data from a local file */
  public loadCountryDetailsLocal(name: string): Observable<Country> {
    return this.http.get('/assets/countries.json').pipe(
      map((list: Array<any>) =>
        list.map(country => this.relevantData(country))
      ),
      map(list => list.find(country => country.name === name))
    );
  }

  public loadCountryDetails(name: string): Observable<Country> {
    return this.http
      .get(`http://localhost:8080/api/countries/${name}`)
      .pipe(map(c => c as Country));
  }

  public updateCountry(id: number, newValue: PartiallyFilledCountry): void {
    this.http.patch(`/api/countries/${id}`, newValue).subscribe();
  }

  public deleteCountry(id: number): void {
    this.http.delete(`/api/countries/${id}`).subscribe();
  }
}
