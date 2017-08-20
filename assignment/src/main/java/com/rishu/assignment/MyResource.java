package com.rishu.assignment;

 
import java.util.List;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
 
import com.rishu.dao.Student;
import com.rishu.dao.StudentDAO;
 
 
@Path("/students")
public class MyResource {
 
    @GET
    @Produces("application/json")
    public List<Student> getStudent() {
        StudentDAO dao = new StudentDAO();
        List students = dao.getStudents();
        return students;
    }
 
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addStudent(Student stu){
        stu.setName(stu.getName());
        stu.setAge(stu.getAge());
                
        StudentDAO dao = new StudentDAO();
        dao.addStudent(stu);
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateStudent(@PathParam("id") int id, Student stu){
        StudentDAO dao = new StudentDAO();
        int count = dao.updateStudent(id, stu);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteStudent(@PathParam("id") int id){
        StudentDAO dao = new StudentDAO();
        int count = dao.deleteStudent(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}