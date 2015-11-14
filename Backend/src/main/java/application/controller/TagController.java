package application.controller;

import application.core.Tag;
import application.miscalleneous.Result;
import application.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private TagRepository tagRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/tags")
    public ArrayList<Tag> getAll(){
        ArrayList<Tag> list =  tagRepo.findAll();
        for(Tag t: list){
            t.setResult(new Result(Result.RESULT_OK, "Tag found"));
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tags/id/{id}")
    public Tag getById(@PathVariable("id")int id){
        Tag tag = tagRepo.findById(id);
        if(tag == null){
            tag = new Tag();
            tag.setResult(new Result(Result.RESULT_FAILED, "Tag not found"));
            return tag;
        }

        tag.setResult(new Result(Result.RESULT_OK, "Tag found"));
        return tag;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tags/tagName/{tagName}")
    public Tag getByName(@PathVariable("tagName") String tagName){
        Tag tag = tagRepo.findByTagName(tagName);
        if(tag == null){
            tag = new Tag();
            tag.setResult(new Result(Result.RESULT_FAILED, "Tag not found"));
        }

        tag.setResult(new Result(Result.RESULT_OK, "Tag found"));
        return tag;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Tag createTag(@RequestParam("tagName") String tagName){
        Tag tag = tagRepo.findByTagName(tagName);
        if(tag != null){
            tag.setResult(new Result(Result.RESULT_FAILED, "Tag has been already created"));
            return tag;
        }

        tag = new Tag(tagName);
        tag.setResult(new Result(Result.RESULT_OK,"Succesfully created"));
        return tag;
    }

    /*Implement delete method*/
}
