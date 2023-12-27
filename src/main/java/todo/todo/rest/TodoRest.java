/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.todo.rest;

import todo.core.entity.Todo;
import todo.core.service.QueryService;
import todo.core.service.TodoService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authz
public class TodoRest {
    
    @Inject
    TodoService todoService;
    @Inject
    QueryService queryService;
    
    @Path("new")
    @POST
    public Response createTodo(Todo todo){
       todoService.createTodo(todo);
       
       return Response.ok(todo).build();
    }
    
    
//    @Path("update")
//    @PUT
//    public Response updateTodo(Todo todo){
//        todoService.updateTodo(todo);
//
//         return Response.ok(todo).build();
//    }
    
    
    @Path("{id}")
    @GET
    public Todo getTodo(@PathParam("id") Long id){
        return queryService.findTodoById(id);
    }
    
    
    
    @Path("list")
    @GET
    public List<Todo> getTodos(){
        return queryService.getAllTodos();
    }

    @Path("status")
    @POST
    public Response markAsComplete(@QueryParam("id") Long id){
        Todo todo = todoService.findToDoById(id);
        todo.setIsCompleted(true);
        todoService.updateTodo(todo);

        return Response.ok(todo).build();

    }



    
}
