export const API_PATH = "http://localhost/api/"
export const TOKEN_VALUE = localStorage.getItem("warehouse-token")
export const HEADER = {
    headers: {Authorization: TOKEN_VALUE}
    // headers: {Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MTI0NTU4OTciLCJpYXQiOjE2Mzg4ODk5MDMsImV4cCI6MTYzODk3NjMwM30.1N1rAJ9OupIRdhs057qozfjC0SPBhJguEr-rL61hHMDwuQYYFRwngOf6dgLVf47BPStMPFbece_O0in9-1UPyg"}
}