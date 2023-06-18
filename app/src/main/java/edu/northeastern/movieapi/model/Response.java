package edu.northeastern.movieapi.model;

public class Response {
    private String results;
    private String errorMsg;

    public Response(String results, String errorMsg) {
        this.results = results;
        this.errorMsg = errorMsg;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
