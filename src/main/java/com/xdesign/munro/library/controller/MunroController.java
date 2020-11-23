package com.xdesign.munro.library.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xdesign.munro.library.helper.Constants;
import com.xdesign.munro.library.models.Munro;
import com.xdesign.munro.library.repository.MunroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xdesign.munro.library.service.MunroService;
import com.xdesign.munro.library.helper.CSVHelper;
import com.xdesign.munro.library.message.ResponseMessage;

@CrossOrigin( "http://localhost:8081" )
@Controller
@RequestMapping( "/api/csv" )
public class MunroController
{
    @Autowired
    MunroService service;
    @Autowired
    MunroRepository munroRepository;

    @PostMapping( "/uploadMunros" )
    public ResponseEntity<ResponseMessage> uploadMunroFile( @RequestParam( "file" ) MultipartFile file )
    {
        String message = "";

        if ( CSVHelper.hasCSVFormat( file ) )
        {
            try
            {
                service.saveMunros( file );

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status( HttpStatus.OK ).body( new ResponseMessage( message ) );
            }
            catch ( Exception e )
            {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status( HttpStatus.EXPECTATION_FAILED ).body( new ResponseMessage( message ) );
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( new ResponseMessage( message ) );
    }

    @GetMapping( "/munros" )
    public ResponseEntity<List<Munro>> getAllMunros(
            @RequestParam( required = false ) String name,
            @RequestParam( defaultValue = "0" ) int page,
            @RequestParam( defaultValue = "10" ) int size, @RequestParam( defaultValue = "id,name" ) String[] sort )
    {
        try
        {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if ( sort[0].contains( "," ) )
            {
                for ( String sortOrder : sort )
                {
                    String[] _sort = sortOrder.split( Constants.COMMA );
                    orders.add( new Sort.Order( getSortDirection( _sort[1] ), _sort[0] ) );
                }
            }
            else
            {
                orders.add( new Sort.Order( getSortDirection( sort[1] ), sort[0] ) );
            }

            List<Munro> munros = new ArrayList<>();
            Pageable pagingSort = PageRequest.of( page, size, Sort.by( orders ) );

            Page<Munro> munroPage;
          if ( name == null )
          {
            munroPage = munroRepository.findAll( pagingSort );
          }
          else
          {
            munroPage = munroRepository.findByName( name, pagingSort );
          }

            munros = munroPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put( Constants.MUNROS, munros );
            response.put( Constants.CURRENT_PAGE, munroPage.getNumber() );
            response.put( Constants.TOTAL_ITEMS, munroPage.getTotalElements() );
            response.put( Constants.TOTAL_PAGES, munroPage.getTotalPages() );

            return new ResponseEntity( response, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    private Sort.Direction getSortDirection( String direction )
    {
        if ( direction.equalsIgnoreCase( Constants.ASC ) )
        {
            return Sort.Direction.ASC;
        }
        else if ( direction.equalsIgnoreCase( Constants.DESC ) )
        {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

}
