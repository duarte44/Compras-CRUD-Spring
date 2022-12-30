package com.compras.resources;

import com.compras.dto.ClienteDTO;
import com.compras.entities.Cliente;
import com.compras.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
        //reporta todas as categorias
    }

       /* @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = service.findAll();
        return ResponseEntity.ok().body(list);
        //reporta todas as categorias
    }
      ----->>>>> RETORNA UMA LISTA DE TODOS OS CLIENTES COM SUAS COMPRAS ASSOCIADAS

    */


    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Long id){
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody ClienteDTO objDto){
        Cliente obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //pega o id do objeto e passa pra url
        return ResponseEntity.created(uri).build();
        /* cria um novo usuario no banco de dados */
    }

    @RequestMapping( value = "/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody ClienteDTO objDto, @PathVariable Long id){
        Cliente obj = service.fromDTO(objDto);
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
