package com.alvion.importer.watcher;

import com.alvion.importer.models.Client;
import com.alvion.importer.process.ClientProcessor;
import com.alvion.importer.reader.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class FileWatcherController {

	@Value("${client.input.folder}")
	private String folder;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private ClientProcessor processor;

	@PostConstruct
	public void start() {
		for (Client client : clientDao.getClientList()) {
			processor.process(client);
		}
		startListener();
	}

	private void startListener() {
		if(Files.exists(Paths.get(folder))) {
			FileSystemWatcher watcher = new FileSystemWatcher(false, 20, 10);
			watcher.addListener(changeSet -> {
				for (ChangedFiles changedFiles : changeSet) {
					for (ChangedFile changedFile : changedFiles) {
						if (ChangedFile.Type.ADD.equals(changedFile.getType()) || ChangedFile.Type.MODIFY.equals(changedFile.getType())) {
							Client client = clientDao.getClient(changedFile.getFile());
							if(client != null) {
								processor.process(client);
							}
						}
					}
				}
			});
			watcher.addSourceFolder(new File(folder));
			watcher.start();
		} else {
			throw new RuntimeException("Folder Inbox is not exist. Please create folder");
		}
	}
}
