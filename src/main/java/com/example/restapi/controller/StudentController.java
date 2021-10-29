package com.example.restapi.controller;

import com.example.restapi.service.StudentService;
import com.example.restapi.dto.StudentDto;
import com.example.restapi.dao.StudentDao;
import com.example.restapi.entity.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
public class StudentController {
    StudentService studentService=new StudentService();
    private String name;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListStudent() {
        return Response.status(Response.Status.OK).entity(StudentDao.getAll()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewSudent(StudentDto studentDto) {
        Student student=new Student(studentDto);
        boolean checkInput=studentService.check(student);
        if(checkInput==false){
            return Response.status(Response.Status.OK).entity(studentService.getError()).build();
        }
        StudentDao.insert(student);
        return Response.status(Response.Status.OK).entity(student).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(StudentDto studentDto) {
        Student student=new Student(studentDto);
        StudentDao.update(student);
        return Response.status(Response.Status.OK).entity(student).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeStudent(@PathParam("id") int id){
        return StudentDao.remove(id) ? "Xóa thành công" : "Xóa thất bại";
    }

    @GET
    @Path("/birthday")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBirthday() {
        return Response.status(Response.Status.OK).entity(StudentDao.cmSinhNhat()).build();
    }


    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearch(@QueryParam("name") String name,@QueryParam("hometown") String hometown, @QueryParam("gender") String gender,  @QueryParam("classname") String classname, @QueryParam("major") String major, @QueryParam("mark")  List<Integer> mark, @QueryParam("birthday") List<Integer> birthday ) {
        return Response.status(Response.Status.OK).entity(StudentDao.search(name,hometown, gender, classname, major,mark, birthday)).build();
    }
}
