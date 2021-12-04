package com.bookstore.admin.pagin;

import com.bookstore.admin.service.UserService;
import com.bookstore.common.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

@AllArgsConstructor
@Getter
public class PagingAndSortingHelper {
    private ModelAndViewContainer model;
    private String moduleURL;
    private String listName;
    private String sortField;
    private String sortDir;
    private String keyword;

    public void updateModelAttributes(int pageNum, Page<?> page){
        List<?> listItems = page.getContent();
        int pageSize = page.getSize();

        long startCount = (pageNum - 1) * pageSize + 1;
        long endCount = startCount + pageSize - 1;

        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute(listName, listItems);
        model.addAttribute("moduleURL", moduleURL);
    }

    public void listEntities(int pageNum, int pageSize, SearchRepository<?, Integer> repo) {
        Sort sort = Sort.by(sortField);

        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<?> page = null;

        if (keyword != null) {
            page = repo.findAll(keyword, pageable);
        } else{
            page = repo.findAll(pageable);
        }

        updateModelAttributes(pageNum, page);
    }

    public Pageable createPageable(int pageSize, int pageNum){
        Sort sort = Sort.by("name");
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        return PageRequest.of(pageNum - 1, pageSize, sort);
    }
}