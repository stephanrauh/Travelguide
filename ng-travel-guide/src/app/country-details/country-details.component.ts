import { Component, OnInit } from "@angular/core";
import { CountriesService } from "../backend/countries/countries.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Observable } from "rxjs";
import { Country } from "../backend/countries/country";

@Component({
  selector: "app-country-details",
  templateUrl: "./country-details.component.html",
  styleUrls: ["./country-details.component.scss"]
})
export class CountryDetailsComponent implements OnInit {
  public name: string;

  public details: Observable<Country>;

  constructor(
    private countriesService: CountriesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(parameters => {
      this.name = parameters.get("name");
      this.details = this.countriesService.loadCountryDetails(this.name);
    });
  }

  public editCountry(name: string) {
    this.router.navigate(["/country-editor", name]);
  }

  public deleteCountry(name: string) {}
}
