# Percolation_Statistics

![alt text](https://raw.githubusercontent.com/JustinElsamah/Percolation_Statistics/master/Screen%20Shot%202017-07-13%20at%2010.35.42%20AM.png)


Monte Carlo simulation:

-Initialize all sites to be blocked.

-Repeat the following until the system percolates:

-Choose a site uniformly at random among all blocked sites.

-Open the site.

-The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.

The model. We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)
