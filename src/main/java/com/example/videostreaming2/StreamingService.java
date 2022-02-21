package com.example.videostreaming2;

import com.azure.spring.autoconfigure.storage.resource.AzureStorageResourcePatternResolver;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreamingService {
    public static Logger logger = LoggerFactory.getLogger(StreamingService.class);

    @Autowired
    private AzureStorageResourcePatternResolver resourceLoader;

    @Autowired
    private BlobServiceClient blobServiceClient;

    public List<BlobItem> getList() {
        return blobServiceClient.getBlobContainerClient("videostorage").listBlobs().stream().collect(Collectors.toList());
    }

    public Mono<Resource> getVideo(String title) {
        return Mono.fromSupplier(() -> resourceLoader.getResource("azure-blob://videostorage/"+title));
    }

}
