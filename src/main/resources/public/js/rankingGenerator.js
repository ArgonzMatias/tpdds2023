

// Define a variable to store the current PDF document
let pdfDoc = null;
let pageNum = 1;
const scale = 1.5;

const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

function renderPage(num) {
  pdfDoc.getPage(num).then(page => {
    const viewport = page.getViewport({ scale });
    canvas.height = viewport.height;
    canvas.width = viewport.width;

    const renderContext = {
      canvasContext: ctx,
      viewport: viewport,
    };

    page.render(renderContext);
  });
}

function loadPDF(pdfURL) {
  pdfjsLib.getDocument(pdfURL).promise.then(pdfDoc_ => {
    pdfDoc = pdfDoc_;
    renderPage(pageNum);
  });
}

document.addEventListener('DOMContentLoaded', function() {
  const pdfSelector = document.getElementById('pdfSelector');
  pdfSelector.addEventListener('change', function() {
    const selectedValue = pdfSelector.value;
    loadPDF(selectedValue);
  });
});