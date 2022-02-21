package com.example.videostreaming2;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
public class StreamingController {
    @Autowired
    private StreamingService service;

    @GetMapping(value = "video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title) {
        return service.getVideo(title);
    }

    @GetMapping(value = "video/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlobItem> getList() {
        return service.getList();
    }

}
