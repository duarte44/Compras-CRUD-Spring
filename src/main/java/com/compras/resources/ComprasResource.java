package com.compras.resources;

import com.compras.dto.ClienteDTO;
import com.compras.dto.ComprasDTO;
import com.compras.entities.Cliente;
import com.compras.entities.Compras;
import com.compras.services.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/compras")
public class ComprasResource {

    @Autowired
    private ComprasService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Compras>> findAll() {
        List<Compras> list = service.findAll();
        return ResponseEntity.ok().body(list);
        //reporta todas as categorias
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<Compras> find(@PathVariable Long id){
        Compras obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody ComprasDTO objDto){
        Compras obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //pega o id do objeto e passa pra url
        return ResponseEntity.created(uri).build();
        /* cria um novo usuario no banco de dados */
    }

    @RequestMapping( value = "/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update( @RequestBody ComprasDTO objDto, @PathVariable Long id){
        Compras obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}
