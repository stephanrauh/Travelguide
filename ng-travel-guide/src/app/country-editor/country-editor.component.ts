import { Component } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { CountriesService } from "../backend/countries/countries.service";
import { Country } from "../backend/countries/country";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-country-editor",
  templateUrl: "./country-editor.component.html",
  styleUrls: ["./country-editor.component.scss"]
})
export class CountryEditorComponent {
  public name: string;

  addressForm = this.fb.group({
    capital: null,
    population: [null, [Validators.required, Validators.pattern(/^\d+$/)]]
  });

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private countriesService: CountriesService
  ) {}

  public ngOnInit() {
    this.route.paramMap.subscribe(parameters => {
      this.name = parameters.get("name");
      this.countriesService.loadCountryDetails(this.name).subscribe(country => {
        this.populateForm(country);
      });
    });
  }

  private populateForm(country: Country) {
    //    this.addressForm.patchValue({ capital: country.capital });
    this.addressForm.patchValue(country);
  }

  onSubmit() {
    alert("Thanks! You've subitted " + JSON.stringify(this.addressForm.value));
    this.countriesService.updateCountry(name, this.addressForm.value);
  }
}
