package com.example.public_issue_reporter.service;

import com.example.public_issue_reporter.exception.ResourceNotFoundException;
import com.example.public_issue_reporter.model.Issue;
import com.example.public_issue_reporter.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Issue not found with id: " + id));
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public void deleteIssue(Long id) {
        Issue issue = getIssueById(id);
        issueRepository.delete(issue);
    }

    public Issue updateIssue(Long id, Issue newIssue) {
        Issue existing = getIssueById(id);
        existing.setTitle(newIssue.getTitle());
        existing.setDescription(newIssue.getDescription());
        existing.setLocation(newIssue.getLocation());
        existing.setStatus(newIssue.getStatus());
        return issueRepository.save(existing);
    }
}
