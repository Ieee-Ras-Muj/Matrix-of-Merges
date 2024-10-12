// Dark mode toggle
document.getElementById('dark-mode-toggle').addEventListener('click', function() {
    document.body.style.backgroundColor = document.body.style.backgroundColor === 'rgb(51, 51, 51)' ? '#f4f4f4' : '#333';
    document.body.style.color = document.body.style.color === 'rgb(255, 255, 255)' ? '#000' : '#fff';
});

// Form submission
document.getElementById('contact-form').addEventListener('submit', function(e) {
    e.preventDefault();
    alert('Form submitted!');
});

// Skill bar animation
function animateSkillBars() {
    const skillBars = document.querySelectorAll('.skill-progress');
    skillBars.forEach(bar => {
        const percent = bar.getAttribute('data-percent');
        bar.style.width = percent + '%';
    });
}

// Call animateSkillBars when the page loads
window.onload = animateSkillBars;

// Deliberately added bug: this function is never called
function scrollToTop() {
    window.scrollTo(0, 0);
}
