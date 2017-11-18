Before start you need to set up:
    
    Artifact(In my case it's web_store)
    
    Set output path for bundling in webpack to your artifact output path 
    
    Open "Run/Debug configuration" : 
        Create new Tomcat Default server
        Add npm task (npm start) before deploying artifact
        Add artifact which you created