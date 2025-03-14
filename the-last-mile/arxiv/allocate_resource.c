int allocate_resource (	struct resource * root,
	struct resource * new,
	resource_size_t size,
	resource_size_t min,
	resource_size_t max,
	resource_size_t align,
	resource_size_t (*alignf) (void *, const struct resource *, resource_size_t, resource_size_t),
	void * alignf_data);
