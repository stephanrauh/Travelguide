import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map, filter } from "rxjs/operators";
import { Observable } from "rxjs";
import { Country } from "./country";

@Injectable({
  providedIn: "root"
})
export class CountriesService {
  constructor(private http: HttpClient) {}

  public countriesByContinent(continent: string): Observable<Array<Country>> {
    return this.http.get("/assets/all.json").pipe(
      map((list: Array<any>) =>
        list.map(country => this.relevantData(country))
      ),
      map(list => list.filter(country => country.continent === continent))
    );
  }

  private relevantData(country: any): Country {
    return {
      name: country.name,
      flag: country.flag,
      continent: country.region,
      population: country.population,
      capital: country.capital
    } as Country;
  }

  public loadCountryDetails(name: string): Observable<Country> {
    return this.http.get("/assets/all.json").pipe(
      map((list: Array<any>) =>
        list.map(country => this.relevantData(country))
      ),
      map(list => list.find(country => country.name === name))
    );
  }
}
