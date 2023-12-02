function uploadCSV() {
    const fileInput = document.getElementById("myFile");
    const file = fileInput.files[0];

    if (!file) {
        alert("Please select a file.");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch("/your-java-endpoint-url", {
        method: "POST",
        body: formData,
    })
    .then((response) => {
        if (response.ok) {
            alert("CSV file uploaded successfully!");
        } else {
            alert("Error uploading the CSV file.");
        }
    })
    .catch((error) => {
        alert("An error occurred: " + error);
    });
}