package de.opitz.consulting.example.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.opitz.consulting.example.demo.ElementNotFoundException;
import de.opitz.consulting.example.demo.entities.Countries;
import de.opitz.consulting.example.demo.repositories.CountriesRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/countries")
class CountriesController {
    Logger log = LoggerFactory.getLogger(CountriesController.class);

    @Autowired
    CountriesRepository repository;

    @GetMapping("")
    public List<Countries> index() {
        return repository.findAll();
    }

    @GetMapping("/{name}")
    public Countries retrive(@PathVariable final String name) {
        final Optional<Countries> element = repository.findByName(name);
        if(!element.isPresent()){
            throw new ElementNotFoundException("Country "+name+" not found.");
        }
        return element.get();
    }

    @GetMapping("/region/{name}")
    public List<Countries> byRegion(@PathVariable final String name) {
        return repository.findAllByRegion(name);
    }

    @GetMapping("/find")
    public Countries byCode( @RequestParam(value="code") final String code ){
        final Optional<Countries> element;
        if(code.length()==2){
            element = repository.findByAlpha2code(code.toUpperCase());
        }else{
            element = repository.findByAlpha3code(code.toUpperCase());
        }

        
        if(!element.isPresent()){
            throw new ElementNotFoundException("Country with code ["+code+"] not found.");
        }
        return element.get();
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody final Countries element){
        final Countries savedElement = repository.save(element);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedElement.getId()).toUri();
 
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteElement(@PathVariable final long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody final Countries element, @PathVariable final long id) {
        final Optional<Countries> elementOptional = repository.findById(id);
 
        if (!elementOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
 
        element.setId(id);
        
        repository.save(element);
 
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialUpdateGeneric(
        @RequestBody final Map<String, Object> updates,
        @PathVariable("id") final Long id) {
            final Optional<Countries> elementOptional = repository.findById(id);
 
            if (!elementOptional.isPresent()){
                return ResponseEntity.notFound().build();
            }
            final Countries element = elementOptional.get();

            element.setId(id);
            for(final Map.Entry<String, Object> entry : updates.entrySet()){
                log.info("   key:" + entry.getKey() + " val:"+ entry.getValue());

                if(entry.getKey().equals("population")){
                    element.setPopulation( Long.parseLong(entry.getValue().toString()) );
                }
                if(entry.getKey().equals("alpha2code")){
                    element.setAlpha2code( entry.getValue().toString() );
                }
                if(entry.getKey().equals("alpha3code")){
                    element.setAlpha3code( entry.getValue().toString() );
                }
                if(entry.getKey().equals("capital")){
                    element.setCapital( entry.getValue().toString() );
                }
                if(entry.getKey().equals("region")){
                    element.setRegion( entry.getValue().toString() );
                }
                if(entry.getKey().equals("subregion")){
                    element.setSubregion( entry.getValue().toString() );
                }
                if(entry.getKey().equals("flag")){
                    element.setFlag( entry.getValue().toString() );
                }
                if(entry.getKey().equals("subregion")){
                    element.setContinent( entry.getValue().toString() );
                }

            }
            repository.save(element);
            
        return ResponseEntity.ok("resource updated");
    }

}