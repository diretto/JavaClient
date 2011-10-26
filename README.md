# JavaClient

A Java Client for the diretto Core Service

## API

Please refer to the API documentation: http://diretto.github.com/diretto-api-doc/v2/diretto/core.html

## Getting Started

	URL apiBaseURL = URLTransformationUtils.adjustAPIBaseURL(new URL("<api-base-url>"));
	String emailAddress = "<system-user-email-address>";
	String password = "<system-user-password>";
	
	SystemSession systemSession = JavaClientManager.INSTANCE.getSystemSession(apiBaseURL, emailAddress, password);
	JavaClient javaClient = JavaClientManager.INSTANCE.getJavaClient(systemSession);
	
	CoreService coreService = javaClient.getCoreService();
	
	// Example: Get the Documents of the last 5 days
	
	ResultSet<DocumentID, Document> documents = coreService.getDocumentsAfter(new DateTime().minusDays(5));
	
	// Example: Get the Storage Service (JavaClientStoragePlugin necessary)
	
	StorageService storageService = (StorageService) javaClient.getService(StorageServiceID.INSTANCE);

## License (not valid for `JavaClient/lib/` and `JavaClient/src/org/diretto/api/client/base/external/`)

	Copyright (c) 2011 Tobias Schlecht, Benjamin Erb

	Permission is hereby granted, free of charge, to any person obtaining
	a copy of this software and associated documentation files (the
	"Software"), to deal in the Software without restriction, including
	without limitation the rights to use, copy, modify, merge, publish,
	distribute, sublicense, and/or sell copies of the Software, and to
	permit persons to whom the Software is furnished to do so, subject to
	the following conditions:

	The above copyright notice and this permission notice shall be
	included in all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
	MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
	NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
	LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
	OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
	WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

