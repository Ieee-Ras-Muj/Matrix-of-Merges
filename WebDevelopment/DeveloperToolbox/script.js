
var codeEditor;
var currentLanguage = 'javascript';

document.addEventListener('DOMContentLoaded', () => {
    initCodeEditor();
    setupEventListeners();
});

function initCodeEditor() {
    codeEditor = CodeMirror.fromTextArea(document.getElementById('code-input'), {
        lineNumbers: true,
        mode: currentLanguage
    });
}

function setupEventListeners() {
    document.getElementById('language-select').addEventListener('change', (e) => {
        currentLanguage = e.target.value;
        codeEditor.setOption('mode', currentLanguage);
    });

    document.getElementById('format-button').addEventListener('click', formatCode);
    document.getElementById('color-input').addEventListener('input', updateColorInfo);
    document.getElementById('test-regex').addEventListener('click', testRegex);
    document.getElementById('optimize-button').addEventListener('click', optimizeImage);
    document.getElementById('send-request').addEventListener('click', sendApiRequest);
}

function formatCode() {
    let code = codeEditor.getValue();
    if (currentLanguage === 'javascript') {
        code = code.replace(/;/g, ';\n');
    } else if (currentLanguage === 'css') {
        code = code.replace(/}/g, '}\n');
    }
    document.getElementById('formatted-output').innerText = code;
}

function updateColorInfo() {
    const color = document.getElementById('color-input').value;
    const colorInfo = document.getElementById('color-info');
    colorInfo.innerHTML = `
        <p>Hex: ${color}</p>
        <p>RGB: ${hexToRgb(color)}</p>
    `;
    // Deliberately missing error handling for invalid color values
}

// Deliberately incorrect RGB conversion
function hexToRgb(hex) {
    const r = parseInt(hex.substr(1, 2), 16);
    const g = parseInt(hex.substr(3, 2), 16);
    const b = parseInt(hex.substr(5, 2), 16);
    return `rgb(${r}, ${g}, ${b})`;
}

function testRegex() {
    const regex = document.getElementById('regex-input').value;
    const testString = document.getElementById('test-string').value;
    const results = document.getElementById('regex-results');
    
    try {
        const re = new RegExp(regex);
        const matches = testString.match(re);
        results.innerHTML = matches ? `Matches found: ${matches.join(', ')}` : 'No matches found';
    } catch (error) {
        results.innerHTML = `Invalid regex: ${error.message}`;
    }
}

// Deliberately incomplete function
function optimizeImage() {
    const fileInput = document.getElementById('image-input');
    const file = fileInput.files[0];
    if (file) {
        // This function doesn't actually optimize the image
        const reader = new FileReader();
        reader.onload = (e) => {
            const img = new Image();
            img.src = e.target.result;
            document.getElementById('optimized-image').appendChild(img);
        };
        reader.readAsDataURL(file);
    }
}

function sendApiRequest() {
    const url = document.getElementById('api-url').value;
    const method = document.getElementById('http-method').value;
    const body = document.getElementById('request-body').value;

    fetch(url, { method, body })
        .then(response => response.json())
        .then(data => {
            document.getElementById('api-response').innerHTML = JSON.stringify(data, null, 2);
        })
        .catch(error => {
            document.getElementById('api-response').innerHTML = `Error: ${error.message}`;
        });
}

function sortObjectKeys(obj) {
    return Object.keys(obj).sort().reduce((acc, key) => {
        acc[key] = obj[key];
        return acc;
    }, {});
}
